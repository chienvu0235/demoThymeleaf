//let listAgent = JSON.parse(localStorage.getItem('listAgent')) ?? [];
let listAgent = [];


document.addEventListener('DOMContentLoaded', () => {
//  const params = { 'page': 0, 'size': 0};
  sendAjaxRequest('/employees', 'GET')
    .then(data => {
      listAgent = data.content;
      renderAgent();
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
});

const $id = document.getElementById('id');
const $name = document.getElementById('name');
const $gender = document.getElementById('gender');
const $phone = document.getElementById('phone');
const $email = document.getElementById('email');
const $section = document.getElementById('section');
const $office = document.getElementById('office');


const $idUpdate = document.getElementById('id_update');
const $nameUpdate = document.getElementById('name_update');
const $genderUpdate = document.getElementById('gender_update');
const $phoneUpdate = document.getElementById('phone_update');
const $emailUpdate = document.getElementById('email_update');
const $sectionUpdate = document.getElementById('section_update');
const $officeUpdate = document.getElementById('office_update');

const $btnNew = document.getElementById('btn_new')
//const $btnCreate = document.getElementById('create');
const $btnEscape1 = document.getElementById('escape1');
const $btnEscape2 = document.getElementById('escape2');
const $btnCancel = document.getElementById('cancel');
const $btnUpdate = document.getElementById('btn_update');

const $modalCreate = document.getElementById('modal_create');
const $modalUpdate = document.getElementById('modal_update');



$btnNew.onclick = () => {
    $modalCreate.style.display = 'flex';
}
const $table = document.getElementById('table');

function convertDtoToLabel(agent) {
  let newAgent = {};
  newAgent.idLabel = agent.id;
  newAgent.nameLabel = agent.name;
  newAgent.genderLabel = parseInt(agent.gender) === 'M' ? 'Nam' : 'Nữ';
  newAgent.phoneLabel = agent.phoneNumber;
  newAgent.emailLabel = agent.email;
  newAgent.officeLabel = agent.department;
  newAgent.roleLabel = convertIdToRole(agent.roleId);

  return newAgent;
}

const renderAgent = (agents = listAgent) => {
    if (!Array.isArray(agents)) {
        return;
    }
    let rowAgent = '';
    for(let agent of agents ) {
      agent.idLabel = agent.id;
      agent.nameLabel = agent.name;
      agent.genderLabel = parseInt(agent.gender) === 'M' ? 'Nam' : 'Nữ';
      agent.phoneLabel = agent.phoneNumber;
      agent.emailLabel = agent.email;
      agent.officeLabel = agent.department;
      agent.roleLabel = convertIdToRole(agent.roleId);

//        agent = convertDtoToLabel(agent);

        rowAgent += `
        <tr >
            <th class="th_stt" scope="row"></th>
            <td>${agent.idLabel}</td>
            <td>${agent.nameLabel}</td>
            <td>${agent.genderLabel}</td>
            <td>${agent.phoneLabel}</td>
            <td>${agent.emailLabel}</td>
            <td>${agent.officeLabel}</td>
            <td>${agent.roleLabel}</td>
            <td>
                <button class="btn btn-success" ><i class="fa-solid fa-pen-to-square" onClick="updateAgent(${agent.id})" ></i></button>
                <button class="btn btn-danger"><i class="fa-solid fa-trash" onClick="deleteAgent(${agent.id})"></i></button>
            </td>
        </tr>
        `;
    }
    $table.innerHTML = rowAgent;
    sinhSTT();
}

function sinhSTT() {
    $('.th_stt').each(function(key, value) {
        $(this).text(key+1);
    });
}
// create
const clearInput = () =>{
    $id.value = '';
    $name.value = '';
    $gender.value = 'Giới tính';
    $phone.value = '';
    $email.value ='';
    $section.value ='';
    $office.value ='Chức vụ';
}



//$btnCreate.onclick = () => {
//  var formData = $(this).serialize();
//    const id = $id.value;
//    const name = $name.value;
//    const gender = $gender.value;
//    const phone = $phone.value;
//    const email = $email.value;
//    const section = $section.value;
//    const office = $office.value;
//
//    for (let agent of listAgent) {
//        if (agent.id === id){
//            document.getElementById('error_id').innerHTML = 'Id đã tồn tại';
//            return;
//        }
//    }
//
//    const newAgent = {
//        id,
//        name,
//        gender,
//        phone,
//        email,
//        section,
//        office,
//    }
//
//    listAgent.push(newAgent);
//    renderAgent();
//    sinhSTT();
//    clearInput();
//    localStorage.setItem('listAgent', JSON.stringify(listAgent));
//    $modalCreate.style.display = 'none';
//
//}

$btnEscape1.onclick = () => {
    $modalCreate.style.display = 'none';
}
// Update

const updateAgent = (idAgent) => {
    $modalUpdate.style.display = 'flex';
    let index = -1;
    for(let i = 0; i < listAgent.length; i++) {
        if (listAgent[i].id == idAgent ){
            index = i;
        }
    }
    let val = listAgent[index];
    $idUpdate.value = val.id;
    $nameUpdate.value = val.name;
    $genderUpdate.value = val.gender;
    $phoneUpdate.value = val.phoneNumber;
    $emailUpdate.value = val.email;
    $sectionUpdate.value = val.roleId;
    $officeUpdate.value = val.department;
}

$btnUpdate.onclick = () => {
    const stt = '';
    const id = $idUpdate.value;
    const name = $nameUpdate.value;
    const gender = $genderUpdate.value;
    const phone = $phoneUpdate.value;
    const email = $emailUpdate.value;
    const section = $sectionUpdate.value;
    const office = $officeUpdate.value;

    const agentUpdate = {
        stt,
        id,
        name,
        gender,
        phone,
        email,
        section,
        office,
    }

    let index = -1;
    for (let i  = 0; i < listAgent.length; i++) {
        if (listAgent[i].id == id) {
            index = i;
        }   
    }
    listAgent[index] = agentUpdate;
    renderAgent();
    $idUpdate.disabled = false;
    $modalUpdate.style.display = 'none';
    sinhSTT();
    localStorage.setItem('listAgent', JSON.stringify(listAgent));
}
$btnEscape2.onclick = () => {
    $modalUpdate.style.display = 'none';
}

const deleteAgent = (idAgent) => {
  const params = { 'id': idAgent};
  sendAjaxRequest('/employees', 'DELETE', null, params)
    .then(() => {
      let index = -1;
        for(let i = 0; i < listAgent.length; i++) {
          if (listAgent[i].id == idAgent ){
            index = i;
          }
        }
        listAgent.splice(index, 1);
        sinhSTT();
        renderAgent();
    })
}



// search
const $keyWordSearch = document.getElementById('keyword_search');
$keyWordSearch.oninput = () =>
{
    const keywordSearch = $keyWordSearch.value;
    let result = listAgent.filter((agent) =>
    agent.name.toLowerCase().includes(keywordSearch.toLowerCase()));
    sinhSTT();
    renderAgent(result);
}
renderAgent();


//function apiFetch(url, method = 'GET', data = null) {
//  url = 'http://localhost:8008' + url;
//  const options = {
//    method: method,
//    headers: {
//        'Content-Type': 'application/json'
//    }
//  };
//
//  if (data) {
//    options.body = JSON.stringify(data);
//  }
//
//  return fetch(url, options)
//    .then(response => {
//      if (!response.ok) {
//          throw new Error('Network response was not ok ' + response.statusText);
//      }
//      return response.json();
//    })
//    .catch(error => {
//      console.error('There was a problem with the fetch operation:', error);
//      throw error;
//    });
//}

function convertIdToRole(roleId) {
  let role = '';
  switch (parseInt(roleId)) {
    case 1:
        role = 'Trưởng phòng';
        break;
    case 2:
        role = 'Phó phòng';
        break;
    case 3:
        role = 'Nhân viên';
        break;
    case 4:
        role = 'Thực tập';
        break;
    default:
        role = 'Unknown';
        break;
  }
  return role;
}

// Add new employee
$(document).ready(function() {
    $('#employeeForm').submit(function(e) {
        e.preventDefault(); // Prevent form submission

        // Serialize form data
        var formData = $(this).serialize();

        // Send AJAX POST request to the API endpoint
        $.ajax({
            type: 'POST',
            url: '/employees',
            data: formData,
            success: function(response) {
                listAgent.push(response);
                renderAgent(listAgent);
            },
            error: function(xhr, status, error) {
                // Handle error
                console.error(error);
                $('#result').html('An error occurred while adding the employee.');
            }
        });
    });
});

// UPDATE AN EMPLOYEE
$("#btn_update").click(function() {
  let employeeDto = {};
  employeeDto.id = $("#id_update").val();
  employeeDto.name = $("#name_update").val();
  employeeDto.gender = $("#gender_update").val();
  employeeDto.phoneNumber = $("#phone_update").val();
  employeeDto.email = $("#email_update").val();
  employeeDto.department = $("#office_update").val();
  employeeDto.roleId = $("#section_update").val();

  sendAjaxRequest("/employees", "PUT", employeeDto)
  .then(response => {
    for (let i = 0; i < listAgent.length; i++) {
        if (listAgent[i].id == response.id) {
          listAgent[i].name = response.name;
          listAgent[i].gender = response.gender;
          listAgent[i].phoneNumber = response.phoneNumber;
          listAgent[i].email = response.email;
          listAgent[i].department = response.department;
          listAgent[i].roleId = response.roleId;
        }
      };
    renderAgent(listAgent);
  });
});

$("#btn_update").click(function() {
  let employeeDto = {};
  employeeDto.id = $("#id_update").val();
  employeeDto.name = $("#name_update").val();
  employeeDto.gender = $("#gender_update").val();
  employeeDto.phoneNumber = $("#phone_update").val();
  employeeDto.email = $("#email_update").val();
  employeeDto.department = $("#office_update").val();
  employeeDto.roleId = $("#section_update").val();

  sendAjaxRequest("/employees", "PUT", employeeDto)
  .then(response => {
    for (let i = 0; i < listAgent.length; i++) {
        if (listAgent[i].id == response.id) {
          listAgent[i].name = response.name;
          listAgent[i].gender = response.gender;
          listAgent[i].phoneNumber = response.phoneNumber;
          listAgent[i].email = response.email;
          listAgent[i].department = response.department;
          listAgent[i].roleId = response.roleId;
        }
      };
    renderAgent(listAgent);
  });
});

async function sendAjaxRequest(url, method, data, params) {
  try {
    url = 'http://localhost:8008' + url;
    // Add the query parameters to the URL
    if (params && Object.keys(params).length > 0) {
      const queryParams = new URLSearchParams(params).toString();
      url += '?' + queryParams;
    }

    const body = !!data ? JSON.stringify(data) : null;

    const response = await fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json'
      },
      body: body
    });

    if (!response.ok) {
      // Handle HTTP errors
      const errorData = await response.json();
      throw new Error(`Error: ${response.status} - ${errorData.message}`);
    }

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    console.error('Error:', error);
  }
}