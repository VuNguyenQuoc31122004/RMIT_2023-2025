<?php
//Check what server you are accessing
if (strstr($_SERVER['SERVER_NAME'], 'localhost')) {
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "RMIT";
} else {
     $servername = "talsprddb02.int.its.rmit.edu.au";
    $username = "s4010423";
    $password = "Bluehacker14";
    $dbname = "s4010423";
}
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}