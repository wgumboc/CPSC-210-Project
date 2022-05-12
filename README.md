# Employee Scheduler
## CPSC 210 Project by William Gumboc

## Background

As a previous Quality Control Supervisor at PepsiCo. I was in charge of managing 25 front line employees. There was no
scheduling software, and creating the schedule involved manipulating an Excel spreadsheet, leading to various mistakes.
These mistakes included scheduling an employee in area in which they do not possess the skills and having a gap
in positions required to be filled. This application serves to simplify the process by attributing skills to various
employees, visually showing the user which positions need to be filled, and alerting the user if there is a
skills-position mismatch.

## Functionality

### Save/Load
The employee scheduler app features data persistence and is able to save and load user data.
![On Load](https://user-images.githubusercontent.com/19580530/168029904-8f6c84bb-559e-492b-9a44-0ead8c5dd7ba.JPG)

### Add/Remove Employee
Employees can be added or removed by clicking on the corresponding buttons on the top left, and following the prompts. 
![Add Employee](https://user-images.githubusercontent.com/19580530/168029990-5f16c8ec-d63d-430a-9687-edcab4dbfa0f.JPG)

Skills can be added to employees, by selecting an employee and then pressing the "Add/Remove Skills" button. A pop-up
window will allow the user to dynamically add or remove skills by selecting skills and pressing the corresponding 
buttons.
![Add Remove Skills](https://user-images.githubusercontent.com/19580530/168030017-7f5dea81-67a7-4833-b8fb-b9b2d93bcd9b.JPG)

### Add/Remove Positions
Similar to the functionality of adding employees, positions can be added by pressing the corresponding
buttons on the top left.
![Add Position](https://user-images.githubusercontent.com/19580530/168030057-350ce09e-05fe-4aa8-9ac8-78dd4adc3152.JPG)

A required skill must be selected in order to add the position. This adds a barrier for the employee to hold the
required skill before an employee can fill the position. 
![Add Skill to Position](https://user-images.githubusercontent.com/19580530/168030075-6ec1123e-c423-4a88-a30c-3f30e61e2a12.JPG)

By selecting an employee and a position, the "Assign Employee" button allows that employee to be assigned to that
position, provided they possess the required skill.
![Assign Employee](https://user-images.githubusercontent.com/19580530/168030088-458fbebc-c5a3-4a3e-bfe7-2b2bdaf860d9.JPG)

If the employee does not possess the required skill, the app will not assign the employee, and will instead alert the 
user that the employee cannot be assigned.
![Cannot Assign](https://user-images.githubusercontent.com/19580530/168030099-4ce6c7e8-b894-49cd-9b62-e2f2e0cc30c6.JPG)
