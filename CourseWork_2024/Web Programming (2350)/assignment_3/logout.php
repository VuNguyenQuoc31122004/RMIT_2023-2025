<?php
// logout.php
session_start();
session_destroy(); // Destroy all session data
header('Location: index.php'); // Redirect to homepage or wherever you want after logout
exit;
?>
