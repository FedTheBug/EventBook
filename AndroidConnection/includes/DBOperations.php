<?php 
 
    class DBOperations{
 
        private $con; 
 
        function __construct(){
 
            require_once dirname(__FILE__).'/DBConnect.php';
            $db = new DbConnect();
            $this->con = $db->connect();
        }
 
        /*CRUD -> C -> CREATE */
        
        # Creates a New User
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
 
        # Handles User Login
        public function userLogin($email, $pass){
            $password = md5($pass);
            $stmt = $this->con->prepare("SELECT id FROM users WHERE email = ? AND password = ?");
            $stmt->bind_param("ss",$email,$password);
            $stmt->execute();
            $stmt->store_result(); 
            return $stmt->num_rows > 0; 
        }
 
        # Gets a User by Email Address
        public function getUserByEmail($email){
            $stmt = $this->con->prepare("SELECT * FROM users WHERE email = ?");
            $stmt->bind_param("s",$email);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }
         
        # Checks Whether The Username and Password exists or not
        private function isUserExist($name, $email){
            $stmt = $this->con->prepare("SELECT id FROM users WHERE name = ? OR email = ?");
            $stmt->bind_param("ss", $name, $email);
            $stmt->execute(); 
            $stmt->store_result(); 
            return $stmt->num_rows > 0; 
        }

        # Gets All The Events From Database
        public function getAllEvents(){
            $today = date("Y-m-d");
            $stmt = $this->con->prepare("select * from events where event_date >= ?");
            $stmt->bind_param("s", $today);
            $stmt->execute();
            return $stmt->get_result()->fetch_all();
        }
 
    }