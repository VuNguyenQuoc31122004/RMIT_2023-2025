<?php
            include('includes/db_connect.inc');
            include('includes/header.inc');
            include('includes/nav.inc');
            
?>
    <body class="gallery">

        <main>
            <h1>Melbourne has a lot to offer!</h1>
            <p>And what better way to discover Melbourne...Your own way. Melbourne International Hotel can serve as a perfect gateway. We cater for either pleasure or business stays.</p>
            <p>Are you ready to explore?</p>
       
            <?php
$sql = "SELECT * FROM facilities";
$result = $conn->query($sql);

echo '<table class="center">';
$counter = 0; // Initialize a counter to keep track of the number of cells in a row

while ($row = $result->fetch_assoc()) {
    if ($counter % 3 === 0) {
        // Start a new row after every 3 cells
        echo '<tr>';
    }

    $price = "POS";
    if ($row["price"] > 0) {
        $price = '$' . $row["price"];
    }

    // Assuming you have an image URL in the database under a column named 'image_url'
    $imageURL = $row["image"];

    echo <<<CDATA
        <td>
        <a href="details.php?facilityid={$row["facilityid"]}"><img src="images/{$row["image"]}" alt="Facility Image"></a> <br>

            <a href="details.php?facilityid={$row["facilityid"]}">{$row["facilityname"]}</a><br>
        </td>
    CDATA;

    $counter++;

    if ($counter % 3 === 0) {
        // End the row after every 3 cells
        echo '</tr>';
    }
}

// Close the last row if the number of cells is not a multiple of 3
if ($counter % 3 !== 0) {
    echo '</tr>';
}

echo '</table>';
?>

        </main>

        <?php
            include('includes/footer.inc');
        ?>
</html>