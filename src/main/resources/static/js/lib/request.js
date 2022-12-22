export async function getData(url = '') {
    // Default options are marked with *
    const response = await fetch(url, {
        method: 'GET', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
    });
    return response; // dont forget to PARSE IT TO JSON with .json()
}

export async function putData(url = '', data = {}) {
    // Default options are marked with *
    const response = await fetch(url, {
        method: 'PUT', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify(data)
    });
    return response; // dont forget to PARSE IT TO JSON with .json()
}

export async function postData(url = '', data = {}) {
    // Default options are marked with *
    const response = await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify(data)
    });
    return response; // dont forget to PARSE IT TO JSON with .json()
}

export async function deleteData(url = '') {
    // Default options are marked with *
    const response = await fetch(url, {
        method: 'DELETE', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
    });
    return response; // dont forget to PARSE IT TO JSON with .json()
}

//odpovědi na různé chyby (neoprávněný přístup, chybný zápis do databáze atd.)
export function checkResponse(responseStatus, msg) {
    let additionaltext = msg ? msg : ""

    if (responseStatus === 200) {
        alert("Zápis do databáze proběhl v pořádku")
    } else if (responseStatus === 403) {
        alert("Přístup byl odepřen [" + responseStatus + "]\n" + additionaltext)
    } else if (responseStatus === 400 || responseStatus === 500) {
        alert("Nastal problém při zápisu do databáze, \n " +
            "\"Poruseni integrity dat - neunikatni nebo nulova povinna data\"\n" + `[${responseStatus}] 
            \n ${additionaltext}`)

    } else {
        alert("Problém při zápisu \n " + additionaltext)
    }
}