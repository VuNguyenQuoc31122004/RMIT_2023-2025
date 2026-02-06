<?php
            include('includes/db_connect.inc');
            include('includes/header.inc');
            include('includes/nav.inc');
            
?>
    <body class="add">
        <Main>
        <h1>Add A facility</h1>
            <p>You can add a facility here</p>
            <form action="process.php" method="post" enctype="multipart/form-data">
                <label for="facility_name" class="required-field">Facility Name:</label><br>
                <input type="text" name="facility_name" required class="text" placeholder="Provide a name for a facility"><br>
                
                <label for="description" class="required-field">Description:</label><br>
                <input name="description" required type="text" class="text" placeholder="Describe the facility briefly"><br>
                
                <div>
                    <label for="image">Select an Image:</label>
                    <input type="file" name="image" id="image" accept="image/*">
                    
                </div>
                
               
                
                <label class="required-field">Image Caption:</label><br>
                <input for="image_caption" type="text" class="text" name="image_caption" placeholder="Describe the image in one word"><br>
               
                <label for="capacity"  class="required-field">Capacity:</label><br>
                <input type="number" required name="capacity" class="text" placeholder="A max number of people"><br>
                
                <label  class="required-field">Price:</label><br>
                <input type="number" name="price" step="0.01" required required class="text" placeholder="$0.00"><br>
                
                <label for="bed_configuration" class="required-field">Bed configuration:</label><br>
                <select name="bed_configuration" required class="text">
                    <option value="Double">1 Double</option>
                    <option value="Queen">1 Queen</option>
                    <option value="King">1 King</option>
                    <option value="TwoSingle">2 Single</option>
                    <option value="N/A">N/A</option>
                </select>

                <div class="button">
                    <button class="button2 button1" type="submit" value="Submit">
                        <span class="material-symbols-outlined">
                        check
                        </span>
                        Submit</button>
                    <button class="button2"><span class="material-symbols-outlined">
                        close
                        </span>clear</button>
                </div>
            </form>
        </Main>
        <?php
            include('includes/footer.inc');
        ?>

