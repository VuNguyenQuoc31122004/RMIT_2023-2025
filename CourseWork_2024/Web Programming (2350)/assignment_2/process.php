<?php
// Database connection parameters
$host = "talsprddb02.int.its.rmit.edu.au";
$username = "s4010423";
$password = "Bluehacker14";
$database = "s4010423";

// Establish a connection to the MySQL database
$mysqli = new mysqli($host, $username, $password, $database);

// Check the connection
if ($mysqli->connect_error) {
    die("Connection failed: " . $mysqli->connect_error);
}

// Check if the form was submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get form data
    $facility_name = isset($_POST['facility_name']) ? $_POST['facility_name'] : '';
    $description = isset($_POST['description']) ? $_POST['description'] : '';
    $image_caption = isset($_POST['image_caption']) ? $_POST['image_caption'] : '';
    $capacity = isset($_POST['capacity']) ? $_POST['capacity'] : 0;
    $price = isset($_POST['price']) ? $_POST['price'] : 0;
    $bed_configuration = isset($_POST['bed_configuration']) ? $_POST['bed_configuration'] : null;
    

    


    // Upload the image to a folder and get its path
    $image_filename = '';

    if (isset($_FILES["image"]) && $_FILES["image"]["error"] == 0) {
        $image_filename = basename($_FILES["image"]["name"]);
        $image_path = "images/" . $image_filename;
        move_uploaded_file($_FILES["image"]["tmp_name"], $image_path);
    }

    // Prepare an SQL statement to insert data into the "facilities" table
    $sql = "INSERT INTO `facilities` (facilityname, description, caption, price, configuration, capacity, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    $stmt = $mysqli->prepare($sql);

    if ($stmt) {
        // Bind parameters
        $stmt->bind_param("ssssdss", $facility_name, $description, $image_caption, $price, $bed_configuration, $capacity, $image_filename);

        // Execute the SQL query
        if ($stmt->execute()) {
            echo "Facility data submitted successfully!";
            
            
            // Redirect the user to the index.php page after successful submission
            header("Refresh: 1; url=index.php");
            
            exit;
        } else {
            echo "Error executing SQL statement: " . $stmt->error;
        }

        $stmt->close();
    } else {
        echo "Error preparing SQL statement: " . $mysqli->error;
    }
}

// Close the database connection
$mysqli->close();
?>
