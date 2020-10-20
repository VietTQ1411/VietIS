const {
  DataTypes
} = require('sequelize');

module.exports = sequelize => {
  const attributes = {
    id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: null,
      primaryKey: true,
      autoIncrement: true,
      comment: null,
      field: "id"
    },
    userID: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "userID",
      references: {
        key: "id",
        model: "User_model"
      }
    },
    tokenKey: {
      type: nvarchar(300),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "tokenKey"
    }
  };
  const options = {
    tableName: "PhoneToken",
    comment: "",
    indexes: []
  };
  const PhoneTokenModel = sequelize.define("PhoneToken_model", attributes, options);
  return PhoneTokenModel;
};