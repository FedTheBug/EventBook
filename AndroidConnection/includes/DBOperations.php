<?php 
 
    class DbOperations{
 
        private $con; 
 
        function __construct(){
 
            require_once dirname(__FILE__).'/DbConnect.php';
            $db = new DbConnect();
            $this->con = $db->connect();
        }
 
        /*CRUD -> C -> CREATE */
 
        public function createUser($name, $pass, $email){
            if($this->isUserExist($name,$email)){
                return 0; 
            }else{
                $password = md5($pass);
                $stmt = $this->con->prepare("INSERT INTO `users` (`id`, `name`, `password`,`email`, `email_verified_at`, `remember_token`, `created_at`, `updated_at`)
                                                        VALUES (NULL, ?, ?, ?,NULL, NULL, NULL, NULL);");
                $stmt->bind_param("sss", $name, $password, $email);
 
                if($stmt->execute()){
                    return 1; 
                }else{
                    return 2; 
                }
            }
        }
 
        public function userLogin($name, $pass){
            $password = md5($pass);
            $stmt = $this->con->prepare("SELECT id FROM users WHERE name = ? AND password = ?");
            $stmt->bind_param("ss",$name,$password);
            $stmt->execute();
            $stmt->store_result(); 
            return $stmt->num_rows > 0; 
        }
 
        public function getUserByName($name){
            $stmt = $this->con->prepare("SELECT * FROM users WHERE name = ?");
            $stmt->bind_param("s",$name);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }
         
 
        private function isUserExist($name, $email){
            $stmt = $this->con->prepare("SELECT id FROM users WHERE name = ? OR email = ?");
            $stmt->bind_param("ss", $name, $email);
            $stmt->execute(); 
            $stmt->store_result(); 
            return $stmt->num_rows > 0; 
        }
 
    }