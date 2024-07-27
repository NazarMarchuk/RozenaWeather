#include "form.h"
// #include <QIcon>

void clearWidgets(QWidget *parent)
{
    QList<QWidget *> widgets = parent->findChildren<QWidget *>();
    foreach (QWidget *widget, widgets) {
        widget->deleteLater();
    }
}

Form::Form(QWidget *parent)
    : QWidget{parent}
{

    this->setWindowTitle("Rozena weather");
    this->resize(300, 300);
    this->show();

    layout = new QVBoxLayout(this);
    registerButton = new QPushButton("Register", this);
    layout->addWidget(registerButton);
    loginButton = new QPushButton("Login", this);
    layout->addWidget(loginButton);

    connect(registerButton, &QPushButton::clicked, this, &Form::registrtionForm);
    connect(loginButton, &QPushButton::clicked, this, &Form::loginForm);

    // trayIcon = new QSystemTrayIcon(this);
    // trayIcon->setIcon(QIcon::fromTheme("WeatherClear", QIcon(":/icons/tray_icon.png")));  // Використання іконки з теми з запасною іконкою
    // trayIcon->show();
    // showNotification("stub");

}

void Form::registrtionForm(){
    clearWidgets(this);
    Register* reg = new Register(this);
    reg->show();
}

void Form::loginForm(){
    clearWidgets(this);
    Login *login = new Login(this);
    login->show();
}

// void Form::showNotification(const QString &message)
// {
//     trayIcon->showMessage("Wather data", message, QSystemTrayIcon::Information, 3000);
// }





