#ifndef REGISTER_H
#define REGISTER_H

#include "mainLayout.h"
#include "hash.h"
#include "dbmanager.h"
#include "popupdialog.h"
#include <QDebug>
#include <QWidget>
#include "login.h"
#include "dbmanager.h"
#include "mainLayout.h"


class Register: public QWidget
{
    Q_OBJECT
private:
    QPushButton *enterButton;
    QPushButton *backButton;
    QLineEdit *login;
    QLineEdit *password;
    QLineEdit *location;
    QVBoxLayout* layout;
public:
    explicit Register(QWidget *parent = nullptr);
private slots:
    void inputCheck();
    void addUser();
    bool userExist();
    void stepBack();
};


#endif // REGISTER_H
