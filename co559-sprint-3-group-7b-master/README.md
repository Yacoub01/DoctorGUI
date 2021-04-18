# CO559-Software Development

CO559 Software Development Assessment A2
Group 7B
Doctor Interface

---

## About running the project

This project is created using Java 13, and so will only work with that version or above.

### Dependencies

The project requires files in `/libs/` to be added to the class path of the project.
If you are running an IDE that can handle Marven, the `pom.xml` file will automatically add these dependencies.
If not, please add the following:
* `libs/jbcrypt-0.4.jar` - Used for password encryption/verification to and from the database.
* `libs/junit-4.12.jar` - Used to run JUnit testing.
* `lisb/mysql-connector-java-5.1.41.jar` - Used to interact between the SQL server and the program.

The project also requires you to be connected to the Kent VPN `vega.kent.ac.uk`

The main program can be run, so long as the dependency are installed, from the `src/main/java/com/gitlab/co559/group7b/sprint3/Main.java`

The program JUnit tests can be run, so long as the dependencies are installed, from the `src/main/java/com/gitlab/co559/group7b/sprint3/UnitTesting.java`

---

## Sprint 1

### Logging into the program and getting example dates

There are three different months when example of bookings in the database:

##### Case 1:
* login as `ya217` with password `test2`
* Select bookings for the month `8` and year `2022`
* You will see 3 bookings

##### Case 2:
* login as `jrs63` with password `test4`
* Select bookings for the month `3` and year `2021`
* You will see 4 bookings

##### Case 3:
* login as `jrs63` with password `test4`
* Select bookings for the month `4` and year `2021`
* You will see 2 bookings

---

## Sprint 2
#### Loging as user and enter a bookings.

In sprint 2 we developed 4 separate user stories, these can be tested using the following cases:

#### Enter Visit Details:
* Login as `ya217`with password `test2`
* Select bookings for the month `8`and year `2022`
* You will see 3 bookings, select one of those bookings and press the view booking button
* A new frame will appear with another button `enter visit details` when pressed it will move you to a new frame
* You will then be able to add in any relevant details and confirm and send, reviecing a message aftwerwards


#### View All Patients:
* Login as `ya217`with password `test2`
* Press the view all patients button
* Afterwards a new frame will pop up and show you all patients that are registered in the clinic 


#### View Own Patients:
* Login as `ya217`with password `test2`
* Press the view own patients button
* Afterwards a new frame will pop up and show you all patients that are registered to that doctor in the clinic 


#### Assign a Patient to a Doctor:
* Login as `ya217`with password `test2`
* After pressing the view all patients button
* You will be taken to a new frame with all patients in the clinic 
* You will then see a button for `Assign new doctor`press the button after selecting a specific patient from the list.
* Afterwards a new frame will pop up with all available doctors and a button `Assign Doctor`
* After selecting a doctor from the list press the button shown and a message will the be sent to the doctor in the welcome screen confirming changes.

---

## Sprint 3

In sprint 3 we developed 4 separate user stories, these can be tested (bar one) using the following cases:

#### View Visit Details:
* Login as `jrs63` with password `test4`
* Select bookings that doctor has for the month `03`, and the year `2021`, and press submit.
* Select the forth booking for the patient `Fernando Carter` (a booking that is completed), and press the `View` button
* A window will come up with the details of a patient.
* Press the `View/Edit Details` button.

####Edit Visit Details:
* Login as `jrs63` with password `test4`
* Select bookings that doctor has for the month `03`, and the year `2021`, and press submit.
* Select the forth booking for the patient `Fernando Carter` (a booking that is completed), and press the `View` button
* A window will come up with the details of a patient.
* Press the `View/Edit Details` button.
* Press the `Edit Bookings` button, the text fields and menus will become unlocked and modified.
* Once finished, press the `Confirm and Send` button.
* To cancel, press `Done Viewing`

####Send A message to an Admin/Receptionist:
* Login to the program as any doctor (`ya217`with password `test2` will do)
* Press the `Send new message` button in the `Messages` section of the welcome screen
* You will be presented with a new window, there you can select what user type you wish to send a message to using the dropdown menu (`Doctor`, `Patient`, `Admin`, `Receptionist`)
* You will then be given another dropdown menu with a list of emails from that user type. Select the one you wish to send a message to.
* Enter a `Subject` line and a `Message` and press the `Send` button to submit.
* Press the `<- Back` button to cancel

####Log all access from a user

Any form of access to the database is logged, as well as a message of what is logged.
As a doctor, you have no way to see this.
This would only be for `Administrators` to view.

---

## Video Demonstration

View a 1080p video demonstration of the video using the following link [https://www.youtube.com/watch?v=-kcKzTlWO54](https://www.youtube.com/watch?v=-kcKzTlWO54).

---

## Authors

* Yacoub Alkaradsheh @ya217 (Sometimes `Y.K` or `Yacoub Alkaradsheh` in commits)
* Joshua Sylvester @jrs63
* Gjyri Vegheim @ghwv2 (`helenawerp` in commits)
* Luke Hadley @lth20
* Tejaswini Parmessur @tp379



