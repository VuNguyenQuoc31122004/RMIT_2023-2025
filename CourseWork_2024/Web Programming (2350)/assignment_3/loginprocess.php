<?php
session_start();

// Include the database connection file
include('includes/db_connect.inc');

// Check if the form was submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $input_username = $_POST['username'];
    $input_password = $_POST['password'];

    // Prepare an SQL statement to fetch user details from the "users" table
    $sql = "SELECT * FROM `users` WHERE username = ?";
    $stmt = $conn->prepare($sql); // Here we're using $conn, which is the connection object from db_connect.inc

    if ($stmt) {
        // Bind parameters
        $stmt->bind_param("s", $input_username);
    
        // Execute the SQL query
        $stmt->execute();
    
        // Get the result
        $result = $stmt->get_result();
        if ($row = $result->fetch_assoc()) {
            if (password_verify($input_password, $row['password'])) {
                // Password is valid
    
                $_SESSION['logged_in'] = true; // <-- Set the logged-in flag in the session
                $_SESSION['username'] = $input_username;  // Start user session
    
                echo "Login successful!";
                // Redirect to a secure page or dashboard after successful login
                header("Location: index.php");
                exit;
            } else {
                // Redirect back to the login page with an error message
                header("Location: login.php?error=invalidpassword");
                exit;
            }
        } else {
            header("Location: login.php?error=usernotfound");
            exit;
        }
        $stmt->close();
    } else {
        echo "Error: " . $conn->error;
    }
}

// Close the database connection
$conn->close();
?>
