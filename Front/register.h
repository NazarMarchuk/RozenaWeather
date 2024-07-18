#ifndef REGISTER_H
#define REGISTER_H

#include "form.h"
#include "hash.h"
#include "dbmanager.h"
#include "popupdialog.h"
#include <QDebug>

class Register: public QWidget
{
    Q_OBJECT
private:
    QPushButton *enterButton;
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
};


#endif // REGISTER_H
