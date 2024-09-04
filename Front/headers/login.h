#ifndef LOGIN_H
#define LOGIN_H
#include "mainLayout.h"
#include <QDebug>
#include "dbmanager.h"
#include "popupdialog.h"
#include "dbmanager.h"
#include "hash.h"

const QString DB_PATH = "/Users/bohdanborshchevskyi/Documents/git/RozenaWeather/Front/";
const QString DB_NAME = "user_login_data.db";


class Login: public QWidget
{
    Q_OBJECT
private:
    QPushButton *enterButton;
    QPushButton *backButton;
    QLineEdit *login;
    QLineEdit *password;
    QVBoxLayout* layout;
public:
    explicit Login(QWidget *parent = nullptr);
private slots:
    void inputCheck();
    bool userExist();
    void stepBack();
};

#endif // LOGIN_H
