// popupdialog.cpp
#include "popupdialog.h"
#include <QLabel>
#include <QPushButton>
#include <QVBoxLayout>

PopupDialog::PopupDialog(const QString& text, QWidget *parent)
    : QDialog(parent)
{
    // Create label and button
    m_label = new QLabel(text, this);
    m_okButton = new QPushButton("OK", this);

    // Create layout
    QVBoxLayout *layout = new QVBoxLayout(this);
    layout->addWidget(m_label);
    layout->addWidget(m_okButton);

    // Connect OK button signal to close the dialog
    connect(m_okButton, &QPushButton::clicked, this, &PopupDialog::accept);
}

PopupDialog::~PopupDialog()
{
}
