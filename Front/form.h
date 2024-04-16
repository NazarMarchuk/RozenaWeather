#ifndef FORM_H
#define FORM_H

#include <QWidget>
#include <QVBoxLayout>
#include <QPushButton>
#include <QLineEdit>
#include <QLabel>

class Form : public QWidget
{
    Q_OBJECT
private:
    QPushButton *loginButton;
    QPushButton *registerButton;
    QVBoxLayout* layout;
public:
    explicit Form(QWidget *parent = nullptr);

private slots:
    void registrtionForm();
    void loginForm();
    void clearWidgets();
signals:
};

#endif // FORM_H
