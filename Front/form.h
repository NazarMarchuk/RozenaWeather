#ifndef FORM_H
#define FORM_H

#include <QWidget>
#include <QVBoxLayout>
#include <QPushButton>
#include <QLineEdit>
#include <QLabel>
#include <QSystemTrayIcon>
#include <QDebug>
#include "dbmanager.h"
#include "popupdialog.h"
#include "login.h"
#include "register.h"

void clearWidgets(QWidget *parent);

class Form : public QWidget
{
    Q_OBJECT
private:
    QPushButton *loginButton;
    QPushButton *registerButton;
    QVBoxLayout* layout;
    QSystemTrayIcon *trayIcon;
public:
    explicit Form(QWidget *parent = nullptr);

private slots:
    void registrtionForm();
    void loginForm();
    // showNotification(const QString &message);
signals:
};

#endif // FORM_H
