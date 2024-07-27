// popupdialog.h
#ifndef POPUPDIALOG_H
#define POPUPDIALOG_H

#include <QDialog>

class QLabel;
class QPushButton;

class PopupDialog : public QDialog
{
    Q_OBJECT

public:
    explicit PopupDialog(const QString& text = "text", QWidget *parent = nullptr);
    ~PopupDialog();

private:
    QLabel *m_label;
    QPushButton *m_okButton;
};

#endif // POPUPDIALOG_H
