<?php
session_start();

include("includes/db_connect.inc");

// Get posted data
$username = $_POST['username'];
$password = $_POST['password'];

// Check if the username already exists
$sql_check = "SELECT * FROM users WHERE username = ?";
$stmt_check = $conn->prepare($sql_check);
$stmt_check->bind_param("s", $username);
$stmt_check->execute();

$result_check = $stmt_check->get_result();

if ($result_check->num_rows > 0) {
    $_SESSION['err'] = "Username already exists. Please choose a different one.";
} else {
    // Insert new user's details into the database
    $sql_insert = "INSERT INTO users (username, password) VALUES (?, SHA(?))";
    $stmt_insert = $conn->prepare($sql_insert);
    $stmt_insert->bind_param("ss", $username, $password);
    
    if ($stmt_insert->execute()) {
        $_SESSION['usrmsg'] = "Registration successful! You can now log in.";
    } else {
        $_SESSION['err'] = "Error during registration. Please try again.";
    }
}

$conn->close();
header("Location:index.php");
exit(0);
?>
