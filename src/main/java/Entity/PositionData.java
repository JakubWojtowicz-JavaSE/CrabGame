package Entity;

import java.awt.*;

public class PositionData {

    private Rectangle solidArea = new Rectangle();

    private int xPos, yPos, width, height, collLeftSpace, collRightSpace, collTopSpace, collBottomSpace;

    public PositionData(int xPos, int yPos, int width, int height, int collLeftSpace, int collRightSpace, int collTopSpace, int collBottomSpace) {
        changeData(xPos, yPos, width, height, collLeftSpace, collRightSpace, collTopSpace, collBottomSpace);
    }
    
    private void updateRectData() {
        solidArea.x = xPos + collLeftSpace;
        solidArea.y = yPos + collTopSpace;
        solidArea.width = width - collLeftSpace - collRightSpace;
        solidArea.height = height - collTopSpace - collBottomSpace;
    }
    
    public void changeData(int xPos, int yPos, int width, int height, int collLeftSpace, int collRightSpace, int collTopSpace, int collBottomSpace) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.collLeftSpace = collLeftSpace;
        this.collRightSpace = collRightSpace;
        this.collTopSpace = collTopSpace;
        this.collBottomSpace = collBottomSpace;
        
        updateRectData();
    }

    public int getColX() {
        return solidArea.x;
    }

    public void setColX(int x) {
        solidArea.x = x;
        xPos = solidArea.x - collLeftSpace;
    }

    public void setColY(int y) {
        solidArea.y = y;
        yPos = solidArea.y - collTopSpace;
    }

    public int getColY() {
        return solidArea.y;
    }

    public int getColWidth() {
        return solidArea.width;
    }

    public int getColHeigth() {
        return solidArea.height;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
        updateRectData();
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
        updateRectData();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        updateRectData();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateRectData();
    }

    public void setCollSpaces(int collLeftSpace, int collRightSpace, int collTopSpace, int collBottomSpace) {
        this.collLeftSpace = collLeftSpace;
        this.collRightSpace = collRightSpace;
        this.collTopSpace = collTopSpace;
        this.collBottomSpace = collBottomSpace;

        updateRectData();
    }
    public int getCollLeftSpace() {
        return collLeftSpace;
    }

    public void setCollLeftSpace(int collLeftSpace) {
        this.collLeftSpace = collLeftSpace;
        updateRectData();
    }

    public int getCollRightSpace() {
        return collRightSpace;
    }

    public void setCollRightSpace(int collRightSpace) {
        this.collRightSpace = collRightSpace;
        updateRectData();
    }

    public int getCollTopSpace() {
        return collTopSpace;
    }

    public void setCollTopSpace(int collTopSpace) {
        this.collTopSpace = collTopSpace;
        updateRectData();
    }

    public int getCollBottomSpace() {
        return collBottomSpace;
    }

    public void setCollBottomSpace(int collBottomSpace) {
        this.collBottomSpace = collBottomSpace;
        updateRectData();
    }

    public Rectangle getCollider() {
        return solidArea;
    }
}
