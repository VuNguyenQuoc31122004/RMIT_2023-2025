<?php
include('includes/db_connect.inc');
include('includes/header.inc');
include('includes/nav.inc');

// Fetch distinct configurations from the database
$sqlConfig = "SELECT DISTINCT configuration FROM facilities WHERE configuration IS NOT NULL AND configuration != ''";
$configurations = $conn->query($sqlConfig);

// Filter based on selected configuration
$selectedConfig = isset($_POST['configurationFilter']) ? $_POST['configurationFilter'] : '';
if ($selectedConfig) {
    $sql = "SELECT * FROM facilities WHERE configuration = '$selectedConfig'";
} else {
    $sql = "SELECT * FROM facilities";
}
$result = $conn->query($sql);

?>
<body class="gallery">

<main>
    <div class="center">
        <h1>Melbourne has a lot to offer!</h1>
        <p>And what better way to discover Melbourne...Your own way. Melbourne International Hotel can serve as a perfect gateway. We cater for either pleasure or business stays.</p>
        <p>Are you ready to explore?</p>
        <form id="filterForm" action="" method="post">
        <select class="select" name="configurationFilter" id="configurationFilter" onchange="this.form.submit()">
            <option value="">All Configurations</option>
            <?php
            while ($configRow = $configurations->fetch_assoc()) {
                $selectedAttribute = ($configRow['configuration'] == $selectedConfig) ? "selected" : "";
                echo "<option value='" . $configRow['configuration'] . "' $selectedAttribute>" . $configRow['configuration'] . "</option>";
            }
            ?>
        </select>
    </form>
    </div>

    <!-- Dropdown menu for configurations -->


    <div class="gallery-container">
        <?php
        while ($row = $result->fetch_assoc()) {
            echo <<<HTML
            <div class="gallery-item">
                <a href="details.php?facilityid={$row["facilityid"]}">
                    <img src="images/{$row["image"]}" alt="Facility Image">
                    <span>{$row["facilityname"]}</span>
                </a>
            </div>
HTML;
        }
        ?>
    </div>
</main>

<?php
    include('includes/footer.inc');
?>
