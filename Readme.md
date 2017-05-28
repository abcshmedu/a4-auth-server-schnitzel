#Authentication Service

Gruppe: Nico Dassler, Philipp Konopac

Heroku: http://auth-schnitzel.herokuapp.com/ (Hier ist die Shareit-HTML Seite zu sehen, diese hat hier aber keine Funktion)

##HTTP Anfragen:

###Autorisierung (login):
URL: https://auth-schnitzel.herokuapp.com/shareit/auth/
Methode: POST
Header: Content-Type: application/json; Accept: application/json
Content: {"name": "Max", "password": "password"}  

###Validation:
URL: https://auth-schnitzel.herokuapp.com/shareit/auth/token
Methode: POST
Header: Content-Type: application/json; Accept: application/json
Content: {"token": "<token>"} -> <token> ist das vom login zurückgegebenen token


Der User "Max" mit dem Passwort "password" ist standardmaeßig eingetragen. Das
token muss bei requests and den Resourcenservice mitgeschickt werden.
Das mitschicken passiert auf folgende Weise: [shareit url]?token=[token]

Die Validierungsfunktion wird lediglich vom Resourcenservice verwendet.
