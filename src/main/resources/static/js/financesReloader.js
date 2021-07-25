
    var monthSelect = document.getElementById("month");
    var yearSelect = document.getElementById("year");

    let monthValue = document.getElementById("monthValue").innerText;
    let yearValue = document.getElementById("yearValue").innerText;
    if( monthValue != null && yearValue != null){

         for(let i = 0; i < monthSelect.length; i++){
            let itemValue = monthSelect.options[i].innerText;
            if(itemValue == monthValue) monthSelect.selectedIndex = i;
         }

         for(let i = 0; i < yearSelect.length; i++){
            let itemValue = yearSelect.options[i].innerText;
            if(itemValue == yearValue) yearSelect.selectedIndex = i;
         }
    }

    function reloadPage(){
        let month = monthSelect.value;
        let year = yearSelect.value;
        console.log("reload");
        window.location.href = '/finances/' + year + "/" + month;
    }
