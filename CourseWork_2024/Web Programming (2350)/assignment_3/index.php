<?php
include('includes/db_connect.inc');
include('includes/header.inc');
include('includes/nav.inc');
?>
<body class="home">
<main>
    <!-- Wrap both the carousel and text in a Bootstrap row -->
    <div class="row">

        <!-- Carousel -->
        <div class="col-md-6">
            <div id="carousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <?php
                $active = "active"; // Set the $active variable to ensure the first image is displayed when the page loads.
                // SQL query to select image and caption from the facilities table, ordering by facilityid in descending order, and limiting to 4 results.
                // This ensures that you get the latest 4 images based on the facilityid, assuming facilityid is incremented automatically (e.g., AUTO_INCREMENT).
                $sql = "SELECT image, caption FROM facilities ORDER BY facilityid DESC LIMIT 4";
                $result = $conn->query($sql); // Execute the query
                if (!$result) {
                    die("Query failed: " . $conn->error); // If the query fails, stop the script and display an error message.
                }
                // Loop through the result set. This loop will iterate at most 4 times, displaying each image in a carousel item.
                while ($row = $result->fetch_assoc()) {
                    $image = $row['image']; // Get the image filename
                    $caption = $row['caption']; // Get the image caption
                    // Display the image in a carousel item. The first image will have the "active" class, making it visible.
                    echo <<<END
                <div class="carousel-item $active">
                <img src="images/$image" alt="$caption" class="d-block w-100">
                </div>
                END;
                    $active = ""; // After the first image, remove the "active" class for subsequent images.
                }
                ?>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>

        <!-- Welcome Text -->
        <div class="col-md-6">  
            <h1>
                INTERNATIONAL<br>
                MELBOURNE<br>
                HOTEL<br>
            </h1>
            
            <h2>
                Welcome to<br>
                Melbourne<br>
            </h2>
        </div>
    </div> 
</main>
<div class="index">
    <input type="text" class="looking" name="looking" placeholder="I am looking for..."> 
    <input type="text" class="select" name="select" placeholder="Select your favourite way!">  
    <div>
    <input type="submit" value="search" name="search" class="searching">
        </div>

    <h3>Discover Melbourne your own way!</h5>
    <p class="index2P">We know that everybody has its own style and favourite way to spend their leasure time. 
    Melbourne International Hotel has facilities that will satisfy your personal or business needs.</p>
    <p class="index2P">Are you ready to explore?</p>
    <p class="indexSpacing">......</p>
    </div>
    

<?php
include('includes/footer.inc');
?>
</html>
