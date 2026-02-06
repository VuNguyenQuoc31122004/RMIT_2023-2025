<?php
    include('includes/db_connect.inc');
    include('includes/header.inc');
    include('includes/nav.inc');
            
?>
<body class="login">
    <main>
        <h1>Registration Page</h1>
        <form action="registerprocess.php" method="post">
            <div>
                <label for="username">Username:</label>
                <input type="text" class="text" id="username" name="username" required>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" class="text" id="password" name="password" required>
            </div>

            <div>
                <input type="submit" value="Register">
            </div>
        </form>
    </main>
  <?php
    include('includes/footer.inc');
?>