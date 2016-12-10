# Simple-Mail-Client-Server-
A simple client-server communication demo for email service. 

ENGLISH: 

MailThread Class: 
This class implements the Runnable interface so it can execute as a thread. In the contructor the neccessary variables for the input and output streams are being initialized. In the run() method a MailProtocol object is initialized and the server receives the input, processes it and returns the correct answer. 

MailClient Class:
Is a representation of the client. In the contructor the neccessary variables for the input and output streams are being initialized and the connection with the server is being enstablished. The client then sends commands to the server and receives the answer, which is printed on the monitor. 

MailProtocol Class:
This class processes the user input, using the processInput(String input) method, and returns the correct answer. A state variable is used, the value of which is being checked using multiple if-then statements and the neccessary commands are being executed.

EXECUTION PROCESS: 
~coming soon~


GREEK: 

Κλάση MailThread:
Η κλάση αυτή κάνει implement το interface Runnable, έτσι ώστε να εκτελείται
σε νήμα (thread). Στον constructor γίνονται οι κατάλληλες αρχικοποιήσεις των
input και output streams. Στη μέθοδο run( ) αρχικοποιείται ένα αντικείμενο της
κλάσης MailProtocol. 'Υστερα, ο server δέχεται το input του χρήστη, το
επεξεργάζεται και επιστρέφει κατάλληλη απάντηση.

Κλάση MailClient:
Αποτελεί την αναπαράσταση του client. Αρχικά, δηλώνονται τα input και
output streams του client και γίνεται η σύνδεση με τον server. Έπειτα ο client
στέλνει εντολές στον server και δέχεται την απάντηση (την οποία εκτυπώνει
στην οθόνη).

Κλάση MailProtocol:
Η κλάση αυτή μέσω της μεθόδου processInput(String input) επεξεργάζεται το
input από τον χρήστη και επιστρέφει την κατάλληλη απάντηση. Αυτό
επιτυγχάνεται με χρήση της μεταβλητής state, η τιμή της οποίας ελέγχεται με
χρήση πολλαπλών εντολών if-else και εκτελούνται οι κατάλληλες εντολές.

ΠΕΡΙΓΡΑΦΗ ΕΚΤΕΛΕΣΗΣ
Αρχικά, γίνεται η εγκαθίδρυση της επικοινωνίας μεταξύ server και client, και
εκτυπώνεται στο τερματικό του client το μενού με τις διαθέσιμες λειτουργίες.
Ο χρήστης στην συνέχεια πληκτρολογεί το όνομα της επιθυμητής λειτουργίας.
Ο server δέχεται την είσοδο του χρήστη (σε μορφή String) και μέσω της
κλάσης του πρωτοκόλλου, την επεξεργάζεται και επιστρέφει το κατάλληλο
μήνυμα σε μορφή συμβολοσειράς, το οποίο και εκτυπώνεται στο τερματικό του
χρήστη. Ο χρήστης μπορεί να δώσει την επόμενη εντολή κ.ο.κ. Αυτό θα
συμβαίνει μέχρι μία από τις δύο μεριές διακόψει την επικοινωνία (π.χ. ο
χρήστης επιλέγει την εντολή Exit).
