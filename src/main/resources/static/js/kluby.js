import {deleteData, getData, postData, putData} from "./lib/request.js";
import {checkResponse} from "./lib/request.js";

const loadPlayerButtons = document.querySelectorAll('.loadPlayerButton')

loadPlayerButtons.forEach(function (currentBtn) {
    currentBtn.addEventListener('click', getPlayers)
})

const editClubButtons = document.querySelectorAll('.editClubButton')

editClubButtons.forEach(function (currentBtn) {
    currentBtn.addEventListener('click', getOwner)
})

document.getElementById("pridatKlub").addEventListener("click", postClubAndOwner)

function getOwner(e) {
    e.preventDefault()
    let ownerId = e.target.dataset.owner_id
    let clubId = e.target.dataset.club_id

    getData('http://localhost:8080/owner/' + ownerId)
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
                    fillEditOwnerForm(data, ownerId, clubId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

function fillEditOwnerForm(data, ownerId, clubId) {
    let form = document.getElementById("myForm")

    form.querySelector("#nazevKlubu").value = data.club.name
    form.querySelector("#nazevZeme").value = data.club.country
    form.querySelector("#filozofie").value = data.club.philosophyType
    form.querySelector("#typFirmy").value = data.firmType
    form.querySelector("#jmenoV").value = data.ownerFirstName
    form.querySelector("#prijmeniV").value = data.ownerSurName

    let updateButton = form.querySelector("button#ulozitKlub");
    updateButton.setAttribute("data-owner_id", ownerId)
    updateButton.setAttribute("data-club_id", clubId)
    updateButton.addEventListener("click", updateClubAndOwner)

    let deleteButton = form.querySelector("button#smazatKlub");
    deleteButton.setAttribute("data-owner_id", ownerId)
    deleteButton.addEventListener("click", deleteClubAndOwnerWithPlayers)

    form.style.display = "block"
}

function updateClubAndOwner(e) {
    e.preventDefault()
    let ownerId = e.target.dataset.owner_id
    let clubId = e.target.dataset.club_id

    let form = document.getElementById("myForm")

    const club = {
        "id": clubId,
        "name": form.querySelector("#nazevKlubu").value,
        "country": form.querySelector("#nazevZeme").value,
        "philosophyType": form.querySelector("#filozofie").value,
    }

    const owner = {
        "id": ownerId,
        "ownerFirstName": form.querySelector("#jmenoV").value,
        "ownerSurName": form.querySelector("#prijmeniV").value,
        "firmType": form.querySelector("#typFirmy").value,
    }

    putData("http://localhost:8080/club/" + clubId, club)
        .then(
            function (response) {
                checkResponse(response.status, "Problém při zápisu klubu")
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });

    putData("http://localhost:8080/owner/" + ownerId, owner)
        .then(
            function (response) {
                checkResponse(response.status, "Problém při zápisu rvlastníka")
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}

function deleteClubAndOwnerWithPlayers(e) {
    let ownerId = e.target.dataset.owner_id

    deleteData(`http://localhost:8080/ownerAndClubWithPlayers/${ownerId}`)
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

function getPlayers(e) {
    e.preventDefault()
    let clubId = e.target.dataset.club_id
    let clubName = e.target.dataset.club_name

    getData('http://localhost:8080/players/' + clubId)
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

                // Examine the text in the response
                response.json().then(function (data) {
                    fillPlayersTable(data, clubName, clubId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

function fillPlayersTable(data, clubName, clubId) {

    console.log(data)
    let tableNode = document.getElementById("playersNode")

    while (tableNode.firstChild) {
        tableNode.removeChild(tableNode.firstChild);
    }

    let header3 = document.createElement("H3")
    header3.classList.add("velke")
    header3.textContent = `Hráči klubu: '${clubName}'`
    tableNode.appendChild(header3)

    let table = document.createElement("table");
    table.classList.add("table2")
    let head = ["Jméno hráče", "Příjmení hráče", "Věk", "Váha", "Pozice", "Upravit"]

    let headRow = table.insertRow()
    head.forEach((header) => {
        let headCell = document.createElement("TH")
        headCell.innerHTML = header
        headRow.appendChild(headCell)
    })


    data.forEach((player) => {
        let row = table.insertRow()
        row.insertCell(0).appendChild(document.createTextNode(player.playerFirstName))
        row.insertCell(1).appendChild(document.createTextNode(player.playerSurName))
        row.insertCell(2).appendChild(document.createTextNode(player.age))
        row.insertCell(3).appendChild(document.createTextNode(player.weight))
        row.insertCell(4).appendChild(document.createTextNode(player.position))

        let button = document.createElement("BUTTON")
        button.classList.add("uprostred")
        button.classList.add("myButton1")
        button.setAttribute("data-player_id", player.id)
        button.textContent = "Upravit"
        button.addEventListener("click", getPlayer)
        row.insertCell(5).appendChild(button)
    })

    let addNewPlayerButton = document.getElementById("addNewPlayer")
    addNewPlayerButton.style.display = "inline-block"

    let postPlayerButton = document.getElementById("ulozitHraceP")
    postPlayerButton.setAttribute("data-club_id", clubId)
    postPlayerButton.addEventListener("click", postPlayer)

    tableNode.appendChild(table)
    tableNode.hidden = false

}

function getPlayer(e) {
    e.preventDefault()

    let playerId = e.target.dataset.player_id

    getData('http://localhost:8080/player/' + playerId)
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
                    fillEditPlayerForm(data, playerId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

function fillEditPlayerForm(data, playerId) {

    let form = document.getElementById("myForm4")

    form.querySelector("#jmenoUpravit").value = data.playerFirstName
    form.querySelector("#prijmeniUpravit").value = data.playerSurName
    form.querySelector("#vekUpravit").value = data.age
    form.querySelector("#vahaUpravit").value = data.weight
    form.querySelector("#poziceUpravit").value = data.position

    let updateButton = form.querySelector("button#ulozitHrace");
    updateButton.setAttribute("data-player_id", playerId)
    updateButton.addEventListener("click", updatePlayer)

    let deleteButton = form.querySelector("button#smazatHrace");
    deleteButton.setAttribute("data-player_id", playerId)
    deleteButton.addEventListener("click", deletePlayer)

    form.style.display = "block"

}

function updatePlayer(e) {
    e.preventDefault()
    let playerId = e.target.dataset.player_id

    let form = document.getElementById("myForm4")

    const player = {
        "id": playerId,
        "playerFirstName": form.querySelector("#jmenoUpravit").value,
        "playerSurName": form.querySelector("#prijmeniUpravit").value,
        "age": form.querySelector("#vekUpravit").value,
        "weight": form.querySelector("#vahaUpravit").value,
        "position": form.querySelector("#poziceUpravit").value
    }

    putData("http://localhost:8080/player/" + playerId, player)
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

function deletePlayer(e) {
    let playerId = e.target.dataset.player_id

    deleteData("http://localhost:8080/player/" + playerId)
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

function postPlayer(e) {
    e.preventDefault()
    let form = document.getElementById("myForm3")
    let clubId = e.target.dataset.club_id

    const player = {
        "playerFirstName": form.querySelector("#jmenoHraceP").value,
        "playerSurName": form.querySelector("#prijmeniHraceP").value,
        "age": form.querySelector("#vekHraceP").value,
        "weight": form.querySelector("#vahaHraceP").value,
        "position": form.querySelector("#poziceHraceP").value
    }

    postData("http://localhost:8080/player/" + clubId, player)
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

function postClubAndOwner() {
    let form = document.getElementById("novyKlub")

    const data = {
        "name": form.querySelector("#nazevKlubuN").value,
        "country": form.querySelector("#zemeN").value,
        "philosophyType": form.querySelector("#filozofieN").value,
        "ownerFirstName": form.querySelector("#jmenoVlastnikaN").value,
        "ownerSurName": form.querySelector("#prijmeniVlastnikaN").value,
        "firmType": form.querySelector("#typFirmyN").value
    }

    postData("http://localhost:8080/ownerAndClub/", data)
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