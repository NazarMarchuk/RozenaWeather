#include "form.h"
#include <QDebug>
#include "dbmanager.h"
#include "popupdialog.h"
#include "login.h"

Form::Form(QWidget *parent)
    : QWidget{parent}
{

    this->setWindowTitle("Registration form");
    this->resize(300, 300);
    this->show();

    layout = new QVBoxLayout(this);
    registerButton = new QPushButton("Register", this);
    layout->addWidget(registerButton);
    loginButton = new QPushButton("Login", this);
    layout->addWidget(loginButton);

    connect(registerButton, &QPushButton::clicked, this, &Form::registrtionForm);
    connect(loginButton, &QPushButton::clicked, this, &Form::loginForm);

}
void Form::registrtionForm(){

}
void Form::clearWidgets()
{
    // Очищення всіх віджетів, що додані до вікна Form
    QList<QWidget *> widgets = findChildren<QWidget *>();
    foreach (QWidget *widget, widgets) {
        widget->deleteLater();
    }
}
void Form::loginForm(){
    clearWidgets();
    Login *login = new Login(this);
    login->show();
}





