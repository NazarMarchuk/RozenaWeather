#ifndef LOGIN_H
#define LOGIN_H
#include "form.h"
#include <QDebug>
#include "dbmanager.h"
#include "popupdialog.h"

class Login: public QWidget
{
Q_OBJECT
private:
    QPushButton *enterButton;
    QLineEdit *login;
    QLineEdit *password;
    QVBoxLayout* layout;
public:
    explicit Login(QWidget *parent = nullptr);
private slots:
    void inputCheck();
    void addUser();
    bool userExist();
};

#endif // LOGIN_H
