<?php 
 
require_once '../includes/DbOperations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['name']) and isset($_POST['password'])){
        $db = new DbOperations(); 
 
        if($db->userLogin($_POST['name'], $_POST['password'])){
            $user = $db->getUserByName($_POST['name']);
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