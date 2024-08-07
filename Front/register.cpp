#include "register.h"

Register::Register(QWidget *parent)
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

    location = new QLineEdit(this);
    location->setPlaceholderText("Specify location");
    verticalLayout->addWidget(location);

    layout->addLayout(verticalLayout);

    enterButton = new QPushButton("Register", this);
    layout->addWidget(enterButton);

    backButton = new QPushButton("Back", this);
    layout->addWidget(enterButton);

    connect(enterButton, &QPushButton::clicked, this, &Register::inputCheck);
    connect(backButton, &QPushButton::clicked, this, &Register::stepBack);

    this->show();
}

void Register::inputCheck(){
    DbManager database("/Users/bohdanborshchevskyi/Documents/git/RozenaWeather/Front/user_login_data.db");
    QString login = this->login->text();
    QString password = this->password->text();
    if(userExist()){
            qDebug() << "User already exist";
    }else{
        addUser();
        PopupDialog dialog("User succesfully registred");
        dialog.exec();
    }
}

void Register::stepBack(){
    clearWidgets(this);
    Form* form = new Form(this);
    form->show();
}

bool Register::userExist(){
    DbManager database(DB_PATH + DB_NAME);
    QString login = this->login->text();
    if (database.checkIfUserExist(login)){
        return true;
    }else{
        return false;
    }
}

void Register::addUser(){
    DbManager database(DB_PATH + DB_NAME);
    QString login = this->login->text();
    QString password = this->password->text();
    QString location = this->location->text();
    QString hashedPassword = password;
    hash performHashing(hashedPassword);
    if (database.insertUserLoginData(login, hashedPassword, location)){
        qDebug() << "User succesfully added";
        PopupDialog dialog("Succesfully registred");
        dialog.exec();
    }else{
        qDebug() << "User not added";
    }
}

