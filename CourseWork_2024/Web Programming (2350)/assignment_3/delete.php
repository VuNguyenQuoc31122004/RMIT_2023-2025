<?php
include("includes/db_connect.inc");


if (isset($_GET['facilityid'])) {
    $id = intval($_GET['facilityid']); // Ensure it's an integer


    $sql = "DELETE FROM facilities WHERE facilityid = ?";
    $stmt = $conn->prepare($sql);

    if ($stmt) {

        $stmt->bind_param("i", $id);

     
        if ($stmt->execute()) {
            echo "Facility was successfully deleted";
            header("Location: gallery.php");
            exit();
        } else {
            echo "Error deleting record: " . $stmt->error;
        }

        $stmt->close();
    } else {
        echo "Error preparing SQL statement: " . $conn->error;
    }
} else {
    echo "ID not provided!";
}

$conn->close();
?>
