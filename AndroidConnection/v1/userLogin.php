<?php 
 
require_once '../includes/DBOperations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['email']) and isset($_POST['password'])){
        $db = new DBOperations(); 
 
        if($db->userLogin($_POST['email'], $_POST['password'])){
            $user = $db->getUserByEmail($_POST['email']);
            $response['error'] = false; 
            $response['id'] = $user['id'];
            $response['email'] = $user['email'];
            $response['name'] = $user['name'];
        }else{
            $response['error'] = true; 
            $response['message'] = "Invalid Username or Password";          
        }
 
    }else{
        $response['error'] = true; 
        $response['message'] = "Required fields are missing";
    }
}
 
echo json_encode($response);