import {getData, putData, postData, deleteData} from "./lib/request.js";
import {checkResponse} from "./lib/request.js";

const editWorkerButtons = document.querySelectorAll('.editWorker')

editWorkerButtons.forEach(function (currentBtn) {
    currentBtn.addEventListener('click', editWorker)
})

document.getElementById("postWorker").addEventListener("click", postWorker)

//---------------------------------------------ZAMĚSTNANCI--------------------------------------
//vytvoření nového zaměstnance
function postWorker() {

    let form = document.getElementById("newWorkerForm")

    const worker = {
        "firstName": form.querySelector("#firstName").value,
        "sureName": form.querySelector("#sureName").value,
        "role": form.querySelector("#role").value,
        "email": form.querySelector("#emailNew").value,
        "password": form.querySelector("#password").value,
        "phone": form.querySelector("#phone").value
    }

    postData("http://localhost:8080/worker", worker)
        .then(
            function (response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}

// když chci načíst údaje nějakého zaměstnance (v menší tabulce)
function editWorker(e) {
    e.preventDefault()
    let workerId = e.target.dataset.worker_id

    getData("http://localhost:8080/worker/" + workerId, {})
        .then(
            function (response) {

                if (response.status !== 200) {
                    if (response.status === 403) {
                        alert("Přístup byl odepřen: [" + response.status + "]");
                        return;
                    }
                    alert("problem při čtení databáze: [" + response.status + "]")
                    return;
                }
                response.json().then(function (data) {
                    fillWorkerForm(data, workerId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

//po kliknutí na "Upravit" se načte tabulka s údaji daného zaměstnance
function fillWorkerForm(data, workerId) {
    let form = document.getElementById("myForm")

    form.querySelector("#jmeno").value = data.firstName
    form.querySelector("#prijmeni").value = data.sureName
    form.querySelector("#pozice").value = data.role
    form.querySelector("#email").value = data.email
    form.querySelector("#telefon").value = data.phone

    // tlačítko pro uložení pracovníka
    let updateButton = form.querySelector("button#updateWorker");
    updateButton.setAttribute("data-worker_id", workerId)
    updateButton.addEventListener("click", updateWorker)

    // tlačítko pro smazání pracovníka
    let deleteButton = form.querySelector("button#deleteWorker");
    deleteButton.setAttribute("data-worker_id", workerId)
    deleteButton.addEventListener("click", deleteWorker)

    form.style.display = "block"
}

// smazání daného zaměstnance
function deleteWorker(e) {
    let workerId = e.target.dataset.worker_id

    deleteData("http://localhost:8080/worker/" + workerId)
        .then(
            function (response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

// uložení nových údajů již existujícího zaměstnance
function updateWorker(e) {
    e.preventDefault()
    let workerId = e.target.dataset.worker_id

    let form = document.getElementById("myForm")

    const worker = {
        "id": workerId,
        "firstName": form.querySelector("#jmeno").value,
        "sureName": form.querySelector("#prijmeni").value,
        "role": form.querySelector("#pozice").value,
        "email": form.querySelector("#email").value,
        "phone": form.querySelector("#telefon").value
    }

    putData("http://localhost:8080/worker/" + workerId, worker)
        .then(
            function (response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}
