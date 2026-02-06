<?php 
$page_title = "Details of the facilities";
include('includes/db_connect.inc');
include('includes/header.inc');
include('includes/nav.inc');
?>

<main class="details">
    <section id="facilitiesdetail">
        <?php
        $sql = "select * from facilities where facilityid = {$_GET["facilityid"]}";
        $result = $conn->query($sql);
        while ($row = $result->fetch_assoc()) {
            $price = "POS";
            if ($row["price"] > 0) {
                $price = '$' . $row["price"];
            }
            echo <<<CDATA
            <div class="centered-icon-text">
                <p><img src="images/{$row["image"]}"></p>
                <p><span class="material-symbols-outlined">bed</span></p>
                <p>{$row["configuration"]}</p>
                <p><span class="material-symbols-outlined">account_balance_wallet</span></p>
                <p>$price</p>
                <h3 class="center">{$row["facilityname"]}</h3>
                <p>{$row["description"]}</p>
            </div>
            CDATA;

            // Check if the user is logged in
            if (isset($_SESSION['logged_in']) && $_SESSION['logged_in']) {                echo <<<CDATA
                <div class="edit-delete-buttons center">
                    <button class="btn btn-primary square-btn" onclick="location.href='edit.php?facilityid={$row["facilityid"]}'">Edit</button>
                    <button class="btn btn-danger square-btn" onclick="location.href='delete.php?facilityid={$row["facilityid"]}'">Delete</button>
                </div>
                CDATA;
            }
        }
        ?>
    </section>
</main>

<?php
include('includes/footer.inc');
?>
