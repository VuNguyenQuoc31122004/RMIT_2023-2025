<?php
include('includes/db_connect.inc');




if ($_SERVER["REQUEST_METHOD"] == "POST") {
// This line checks if the server has received a POST request, which would be the
// case if a form was submitted.




    $facility_name = isset($_POST['facility_name']) ? $_POST['facility_name'] : '';
    $description = isset($_POST['description']) ? $_POST['description'] : '';
    $image_caption = isset($_POST['image_caption']) ? $_POST['image_caption'] : '';
    $capacity = isset($_POST['capacity']) ? $_POST['capacity'] : 0;
    $price = isset($_POST['price']) ? $_POST['price'] : 0;
    $configuration = isset($_POST['bed_configuration']) && !empty($_POST['bed_configuration']) ? $_POST['bed_configuration'] : 'Not Specified';
//These lines retrieve the data submitted from the form. They use the ternary 
//conditional operator ?: to check if the data exists and, if not, assign default 
//values.




    // Upload the image to a folder and get its path
    $image_filename = '';

    if (isset($_FILES["image"]) && $_FILES["image"]["error"] == 0) {
        $image_filename = basename($_FILES["image"]["name"]);
        $image_path = "images/" . $image_filename;
        move_uploaded_file($_FILES["image"]["tmp_name"], $image_path);
    }
//This code handles the uploaded image. It checks if an image was uploaded 
//without errors, extracts its filename, determines where it should be saved, and
//then moves the uploaded file to the specified location.




    // Prepare an SQL statement to insert data into the "facilities" table
    $sql = "INSERT INTO `facilities` (facilityname, description, caption, price, configuration, capacity, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
//This block prepares an SQL statement to insert the form data into the 
//'facilities' table in the database. It uses prepared statements to avoid SQL
//injection vulnerabilities. The statement is then executed. Any errors during
//preparation or execution are displayed to the user.




    if ($stmt) {
        // Bind parameters
        $stmt->bind_param("sssssss", $facility_name, $description, $image_caption, $price, $configuration, $capacity, $image_filename);

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
$conn->close();

//$_POST is a superglobal array in PHP that contains all the data sent to the
//server via a POST request. When a form is submitted using the POST
//method, the data is accessible through this array.
//
//isset() is a PHP function that checks if a variable or array key exists and
//has been set. It returns true if the variable/array key exists and is not 
//null, otherwise it returns false.
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

?>



