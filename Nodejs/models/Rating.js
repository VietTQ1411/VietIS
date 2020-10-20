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
    foodId: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "foodId",
      references: {
        key: "id",
        model: "Food_model"
      }
    },
    rating: {
      type: DataTypes.FLOAT,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "rating"
    }
  };
  const options = {
    tableName: "Rating",
    comment: "",
    indexes: []
  };
  const RatingModel = sequelize.define("Rating_model", attributes, options);
  return RatingModel;
};