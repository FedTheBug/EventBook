
<?php

/*
*
* Gives HTTP POST Request
* Returns a Response
*
*/
require_once '../includes/DBOperations.php';
$response = array();


if($_SERVER['REQUEST_METHOD'] == 'POST'){
    if(isset($_POST['name']) and
         isset($_POST['email']) and
             isset($_POST['password'])
    )
    {
        #Operate the data further
        
        $db = new DBOperations();

        if($db->createUser(
                $_POST['name'],
                $_POST['password'],
                $_POST['email']
        ))
        {
            $response['error'] = false;
            $response['message'] = "User Created successfully!";
        }
        else{
            $response['error'] = true;
            $response['message'] = "Some error occured please try again";
        }
    }
    else{
        $response['error'] = true;
        $response['message'] = "Required fields are missing";
    }
}
else{
    $response['error'] = true;
    $response['message'] = "Invalid request";
}
echo json_encode($response);