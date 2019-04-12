<?php
/*
*Middleware that connects to the DataBase
*Does CRUD Operations
*
*/
class DBOperations{
    private $con;
    function __construct(){
        require_once dirname(__FILE__).'/DBConnect.php';
        $db = new DBConnect();
        $this->con = $db->connect();
    }
    
    #creates new user
    function createUser($name, $password, $email){  
        if($this->isUserExist($name, $email)){
            return 0;
        }
        else{
            $pass = password_hash($password, PASSWORD_DEFAULT);
            $stmt = $this->con->prepare("INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES ('?', '?', '?', NULL, '?', NULL, NULL, NULL);");
            $stmt->bind_param("sss", $name, $pass, $email);
            if($stmt->execute()){
                return 1;
            }
            else{
                return 2;
            }
        }
    }
}