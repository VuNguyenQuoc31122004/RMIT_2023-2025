<?php 
session_start();
?>
<header>
    <nav>
        <a href="index.php"><img src="images/logo.jpeg" alt="Company Logo" class="logo"></a>
        <a href="index.php">Home</a>
        <a href="facilities.php">Facilities</a>
        <a href="gallery.php">Gallery</a>

        
        <?php if (isset($_SESSION['logged_in']) && $_SESSION['logged_in']) {
                echo '<a href="add.php">Add Facilities</a>';
                echo '<a href="logout.php">Logout</a>';
    
            } else {
                echo '<a href="register.php">Register</a>';
                echo '<a href="login.php">Login</a>';
            }?>
        <input type="text" placeholder="search" class="search"></input>
    </nav>
    </header>