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
    public function createUser($name, $pass, $email){
            $password = md5($pass);
            $stmt = $this->con->prepare("INSERT INTO `users` (`id`, `name`, `password`,`email`, `email_verified_at`, `remember_token`, `created_at`, `updated_at`)
                                                        VALUES (NULL, ?, ?, ?,NULL, NULL, NULL, NULL);");
            $stmt->bind_param("sss", $name, $password, $email);
            if($stmt->execute()){
                return true;
            }
            else{
                return false;
            }
        }
}