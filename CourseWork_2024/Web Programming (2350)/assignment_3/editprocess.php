<?php
// Start the session
session_start();

// Include the database connection
include("includes/db_connect.inc");

// Check if the form has been submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Extract data from the form
    $facility_name = $_POST['facility_name'] ?? '';
    $description = $_POST['description'] ?? '';
    $image_caption = $_POST['image_caption'] ?? '';
    $capacity = $_POST['capacity'] ?? 0;
    $price = $_POST['price'] ?? 0.0;
    $bed_configuration = $_POST['bed_configuration'] ?? 'Not Specified';
    $facilityid = $_POST['facilityid'] ?? 0;  // Assuming you have this field in your form

    // Handle image upload
    $image_filename = '';
    if (isset($_FILES["image"]) && $_FILES["image"]["error"] == 0) {
        $image_filename = basename($_FILES["image"]["name"]);
        $image_path = "images/" . $image_filename;
        move_uploaded_file($_FILES["image"]["tmp_name"], $image_path);
    }

    // Prepare the SQL statement
    $sql = "UPDATE facilities SET 
                facilityname = ?, 
                description = ?, 
                image = ?, 
                caption = ?, 
                capacity = ?, 
                price = ?, 
                configuration = ? 
            WHERE facilityid = ?";

    $stmt = $conn->prepare($sql);

    // Check for any errors in preparation
    if (!$stmt) {
        die("Error preparing statement: " . $conn->error);
    }

    // Bind parameters and execute
    $stmt->bind_param("ssssidsi", 
        $facility_name, 
        $description, 
        $image_filename,  // Note: This should be the filename, not the path
        $image_caption, 
        $capacity, 
        $price, 
        $bed_configuration, 
        $facilityid);

    if ($stmt->execute()) {
        echo "Facility data updated successfully!";
        header("Refresh: 2; url=index.php");  // Redirect after a short delay
    } else {
        echo "Error executing SQL statement: " . $stmt->error;
    }

    // Close the prepared statement and connection
    $stmt->close();
    $conn->close();
}
?>
