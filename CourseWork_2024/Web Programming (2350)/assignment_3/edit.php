<?php
include('includes/db_connect.inc');
include('includes/header.inc');
include('includes/nav.inc');

if (!isset($_GET['facilityid'])) {
    header("Location: some_error_page.php");
    exit;
}

$facilityid = $_GET['facilityid'];
$sql = "SELECT * FROM facilities WHERE facilityid = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $facilityid);
$stmt->execute();
$result = $stmt->get_result();
$facility = $result->fetch_assoc();
$stmt->close();
?>
<body class="add">
    <Main>
    <h1>Edit Facility</h1>
    <p>You can edit the facility details here</p>
    <form action="editprocess.php" method="post" enctype="multipart/form-data">
        <input type="hidden" name="facilityid" value="<?php echo $facility['facilityid']; ?>">
        
        <label for="facility_name" class="required-field">Facility Name:</label><br>
        <input type="text" name="facility_name" required class="text" value="<?php echo $facility['facilityname']; ?>"><br>
        
        <label for="description" class="required-field">Description:</label><br>
        <input name="description" required type="text" class="text" value="<?php echo $facility['description']; ?>"><br>
        
        <div>
            <label for="image">Select an Image:</label>
            <input type="file" name="image" id="image" accept="image/*">
            <p>Current Image: <img src="images/<?php echo $facility['image']; ?>" alt="Current Image" width="100"></p>
        </div>
        
        <label class="required-field">Image Caption:</label><br>
        <input for="image_caption" type="text" class="text" name="image_caption" value="<?php echo $facility['caption']; ?>"><br>
        
        <label for="capacity"  class="required-field">Capacity:</label><br>
        <input type="number" required name="capacity" class="text" value="<?php echo $facility['capacity']; ?>"><br>
        
        <label class="required-field">Price:</label><br>
        <input type="number" name="price" step="0.01" required class="text" value="<?php echo $facility['price']; ?>"><br>
        
        <label for="bed_configuration" class="required-field">Bed configuration:</label><br>
        <select name="bed_configuration" required class="text">
            <!-- Populate options dynamically -->
            <?php
            $configurations = ["Double" => "1 Double", "Queen" => "1 Queen", "King" => "1 King", "TwoSingle" => "2 Single", "N/A" => "N/A"];
            foreach ($configurations as $key => $value) {
                echo "<option value=\"$key\"";
                if ($facility['bed_configuration'] == $key) {
                    echo " selected";
                }
                echo ">$value</option>";
            }
            ?>
        </select>

        <div class="button">
            <button class="button2 button1" type="submit" value="Submit">
                <span class="material-symbols-outlined">check</span>
                Update</button>
            <button class="button2"><span class="material-symbols-outlined">close</span>clear</button>
        </div>
    </form>
</Main>
<?php
include('includes/footer.inc');
?>
