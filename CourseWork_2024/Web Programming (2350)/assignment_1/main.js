document.addEventListener("DOMContentLoaded", function(){
    const pageSelector = document.getElementById("pageSelector");

    pageSelector.addEventListener("change", function(){
        const selectedValue = pageSelector.value;
        if (selectedValue) {
            window.location.href = selectedValue;
        }
    })
}
)