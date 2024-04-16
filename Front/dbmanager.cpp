#include "dbmanager.h"
#include <QDebug>

DbManager::DbManager(const QString& path)
{
    if (!m_db.isOpen()) {
        m_db = QSqlDatabase::addDatabase("QSQLITE");
        m_db.setDatabaseName(path);

        if (!m_db.open())
        {
            qDebug() << "Error: connection with database failed";
        }
        else
        {
            qDebug() << "Database: connection ok";
        }
    }
}

bool DbManager::insertUserLoginData(const QString& login, const QString& password)
{
    QSqlQuery query(m_db);
    bool res = query.prepare("INSERT INTO user_login_data (login, password) VALUES (:login, :password)");
    query.bindValue(":login", login);
    query.bindValue(":password", password);
    if (!query.exec())
    {
        qDebug() << "Error: Failed to insert user login data:" << query.lastError().text();
        return false;
    }

    return true;
}

bool DbManager::checkIfUserExist(const QString& login){
    QSqlQuery query(m_db);
    query.prepare("SELECT COUNT(*) FROM user_login_data WHERE login = :login");
    query.bindValue(":login", login);

    if (!query.exec())
    {
        qDebug() << "Error: Failed to check if user exists:" << query.lastError().text();
        return false;
    }

    // Fetch the result
    if (query.next()) {
        int count = query.value(0).toInt();
        return (count > 0);
    } else {
        qDebug() << "Error: Failed to fetch result for user exists check";
        return false;
    }
}

bool DbManager::checkPassword(const QString& login, const QString&  password){
    QSqlQuery query(m_db);

    query.prepare("SELECT COUNT(*) FROM user_login_data WHERE login = :login AND password = :password");
    query.bindValue(":login", login);
    query.bindValue(":password", password);

    if (!query.exec())
    {
        qDebug() << "Error: Failed to check if password is correct:" << query.lastError().text();
        return false;
    }

    // Fetch the result
    if (query.next()) {
        int count = query.value(0).toInt();
        return (count > 0);
    } else {
        qDebug() << "Error: Failed to fetch result for password check";
        return false;
    }
}

