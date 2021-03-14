
fetch('https://localhost:8443/users/')
    .then(data => data.json())
    .then(users => console.log(users))
    .catch(error => console.log("Error: ", error));

