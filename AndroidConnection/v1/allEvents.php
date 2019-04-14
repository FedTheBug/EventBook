<?php

/*
*
* Handles a GET Request to fatch Events
* returns a response 
* 
*/

require_once '../includes/DBOperations.php';
$response = array();
$response_collection = array();

if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $db = new DBOperations();

    $events = $db->getAllEvents();
    foreach($events as $event){
        $response['error'] = false;
        $response['id'] = $event[0];
        $response['name'] = $event[1];
        $response['venue'] = $event[2];
        $response['event_date'] = $event[3];
        $response['reg_deadline'] = $event[4];
        $response['description'] = $event[5];
        $response['organizer_id'] = $event[6];

        array_push($response_collection, $response);
    }
}

echo json_encode($response_collection);