<?php
            include('includes/db_connect.inc');
            include('includes/header.inc');
            include('includes/nav.inc');
            
?>
    <body class="facilities">
        <main>
            <div class="center">
                <h1 style="font-weight: bold; font-size:large;">Discover Melbourne your own way!</h1>
                <p style="text-align: center;">We know everybody has its own style and favourite way to spend their leasure time. Melbourne International Hotel has facilities that will satisfy your personal or business needs</p>
            </div>
            <div class="content-flex">
                <img src="images/southgatetowilliamstownferry.jpeg" alt="Town Ferry">
                <table>
                    <tr>
                        <td>Facility Type</td>
                        <td>Capacity</td>
                        <td>Bed configuration</td>
                        <td>Price</td>
                    </tr>
                    <?php
                $sql = "select * from facilities ";
                $result = $conn->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $price = "POS";
                    if ($row["price"]> 0){

                    $price = '$'.$row["price"];
                    }
                    echo<<<"CDATA"
                    <tr>
                    <td><a href = "details.php?facilityid={$row["facilityid"]}">{$row["facilityname"]}</a></td>
                    <td>{$row["capacity"]}</td>
                    <td>{$row["configuration"]}</td>
                    <td>$price</td>
                    </tr>
                    CDATA;
                }?>
                </table>
            </div>    
        </main>
        <?php
            include('includes/footer.inc');
        ?>

</html>
