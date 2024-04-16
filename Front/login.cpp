#include "login.h"
#include "dbmanager.h"

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
    connect(enterButton, &QPushButton::clicked, this, &Login::inputCheck);
    this->show();
}

void Login::inputCheck(){
    DbManager database("/Users/bohdan/Documents/cpp_lrn/Regestration_form/user_login_data.db");
    QString login = this->login->text();
    QString password = this->password->text();
    if(userExist()){
        if (database.checkPassword(login, password)){
            qDebug() << "password is correct";
        }else{
            PopupDialog dialog("incorrect password");
            dialog.exec();
        }
    }else{
        PopupDialog dialog("User not found");
        dialog.exec();
    }
}

bool Login::userExist(){
    DbManager database("/Users/bohdan/Documents/cpp_lrn/Regestration_form/user_login_data.db");
    QString login = this->login->text();
    QString password = this->password->text();
    if (database.checkIfUserExist(login)){
        return true;
    }else{
        return false;
    }
}

void Login::addUser(){
    DbManager database("/Users/bohdan/Documents/cpp_lrn/Regestration_form/user_login_data.db");
    QString login = this->login->text();
    QString password = this->password->text();
    if (database.insertUserLoginData(login, password)){
        qDebug() << "User succesfully added";
    }else{
        qDebug() << "User not added";
    }
}
