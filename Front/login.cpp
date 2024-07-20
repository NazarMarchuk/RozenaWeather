#include "login.h"


Login::Login(QWidget *parent)
    :QWidget{parent}{

    this->setWindowTitle("Registration form");
    this->resize(300, 300);

    layout = new QVBoxLayout(this);
    QVBoxLayout* verticalLayout = new QVBoxLayout();

    login = new QLineEdit(this);
    login->setPlaceholderText("Enter Login");
    verticalLayout->addWidget(login);

    password = new QLineEdit(this);
    password->setPlaceholderText("Enter Password");
    verticalLayout->addWidget(password);

    layout->addLayout(verticalLayout);

    enterButton = new QPushButton("Login", this);
    layout->addWidget(enterButton);

    backButton = new QPushButton("Back", this);
    layout->addWidget(enterButton);

    connect(enterButton, &QPushButton::clicked, this, &Login::inputCheck);
    connect(backButton, &QPushButton::clicked, this, &Login::stepBack);

    this->show();
}

void Login::inputCheck(){
    DbManager database(DB_PATH + DB_NAME);
    QString login = this->login->text();
    QString password = this->password->text();
    QString hashedPassword = password;
    hash performHashing(hashedPassword);

    if(userExist()){
        if (database.checkPassword(login, hashedPassword)){
            qDebug() << "password is correct";
            PopupDialog dialog("Succesfully loged in");
            dialog.exec();
        }else{
            PopupDialog dialog("incorrect password");
            dialog.exec();
        }
    }else{
        PopupDialog dialog("User not found");
        dialog.exec();
    }
}

void Login::stepBack(){
    clearWidgets(this);
    Form* form = new Form(this);
    form->show();
}

bool Login::userExist(){
    DbManager database(DB_PATH + DB_NAME);
    QString login = this->login->text();
    if (database.checkIfUserExist(login)){
        return true;
    }else{
        return false;
    }
}

