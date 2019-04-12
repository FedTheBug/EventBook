
<?php

/*
*
*Gives HTTP POST Request
*
*/
if($_SERVER['REQUEST_METHOD'] == 'POST'){
}
else {
    $response['error'] = true;
    $response['message'] = "Invalid request";
}

echo json_encode($response);