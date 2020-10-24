Короче это типа документация
Код никак не закомментирован, не пытайтесь его прочесть
В папке VectoGraph хранится jar проекта, xml проекта и exe проекта. Так же иконка в формате ico и png.
Так же в ней находится VectoGraphManager - интерфейс упрощающий использование моделей и скелетов
Немного данных об использовании программы:


Горячие кнопки:
Были добавлены во время отладки, но показались весьма удобными
[b]  пока кнопка нажата, кость будет отображаться вертикально. Нужно для более удобной подгонки модели
[+]  переместиться на фигуру выше
[-]  Переместится на фигуру ниже
[f5]  запустить/остановить анимацию
[esc]  выход из приложения
[L]  загрузить модель
[S]  сохранить модель
Стрелки - перемещение полотна (Если Shift зажат, то перемещние происходит быстрее)
ЛКМ&перетягивание - перемещение фигуры/кости
ПКМ&перетягивание - Для модели изменение вектора градиента, для модели ЕСЛИ ИДЕТ РАБОТА С МНОГОУГОЛЬНИКОМ установка множества точек подряд, для кости изменение направления кости, для кости ЕСЛИ ИДЕТ РАБОТА С МОДЕЛЬЮ измененеие направления модели


Интерфейс VectoGraphManager требует объявления следующих методов:
public String getFileStr(String src); - метод для получения строки из файла. Передается String src - расположение файла
public void drawFigure(int x, int y,int width, int height,int type, boolean fillFigure, int[]xx, int[]yy); - метод для отрисовки фигур.
Передается int x - X фигуры
int y - У фигуры
int width - Ширина фигуры
int height - Высота фигуры
int type - тип фигуры где
0 - прямоугольник
1 - овал
2 - линия
3 - многоугольник
boolean fillFiure - будет ли переменная залита полностью
int[] xx - массив хранящий в себе точки Х (для многоугольника)
int[] yy - массив хранящий в себе точки Y (для многоугольника)

public void setColor(boolean isGradient, boolean gradientCycled,int redColor, int greenColor,int blueColor,int alpha,int redColor2, int greenColor2,int blueColor2,int alpha2,int gradientX1, int gradientY1,int gradientX2, int gradientY2, int figureX, int figureY); - метод для задачи цвета фигурам
Передается
boolean isGradient - Является ли заливка градиентом
boolean gradientCycled - Зациклован ли градиент
int redColor, int greenColor,int blueColor,int alpha - цвет 1 в формате RGBA
int redColor2, int greenColor2,int blueColor2,int alpha2 - цвет 2 в формате RGBA (для градиента)
int gradientX1, int gradientY1,int gradientX2 - вектор из двух точек, задающий направление градиента
int figureX, int figureY - x, y заливаемой фигуры
!!!Координаты направления градиента лучше всего использовать в формате gradientX1+figureX gradientY1+figureY gradientX2+figureX gradientY2+figureY!!!
public void rotateFigure(int centerX,int centerY, double radian); - метод поворота фигуры где
int centerX - центр вращения фигуры по оси Y
int centerY - центр вращения фигуры по оси Х
int radian - угол поворота в радианах
public void resetRotate(int centerX,int centerY, double radian); - метод возвращения поворота в изначальное состояние  (на передаваемые данные не смотрите они нахрен не нужны хз зачем я их запихнул сюда)


другие методы
public default Skeleton loadSkeleton(String src) - загрузка данных о скелетоне где String src - расположение файла. Возвращает объект Skeleton
public default Skeleton[] loadSkeletonAnimation(String src) - загрузка данных о скелетной анимации где String src - расположение файла. Возвращает массив Skeleton[]
public default Model loadModel(String src) - загрузка данных о модели где String src - расположение файла. Возвращает объект Model
public default Model[]  loadAnimation(String src) - загрузка данных о анимации где String src - расположение файла. Возвращает массив Model[]

public default void drawSkeleton(Skeleton s, int x, int y) - отрисовка скелетона
где
Skeleton s - отрисовываемый скелетон
int x - расположение скелетона по оси x
int y - расположение скелетона по оси у

public default void drawSkeleton(Skeleton s, int x, int y) - отрисовка скелетона
где
Skeleton s - отрисовываемый скелетон
int x - расположение скелетона по оси x
int y - расположение скелетона по оси у

public default void drawSkeleton(Skeleton s, int x, int y, double radian) - отрисовка скелетона с вращениемм относительно центра
где
Skeleton s - отрисовываемый скелетон
int x - расположение скелетона по оси x
int y - расположение скелетона по оси у
double radian - угол поворота в радианах

public default void drawModel(Model m,int x, int y) - отрисовка модели сохраняя оригинальные размеры
Model m - отрисовываемая модель
int x - расположение модели по оси x
int y - расположение модели по оси у

public default void drawModel(Model m,int x, int y, int width, int height) - отрисовка модели с регулироемыми размерами
Model m - отрисовываемая модель
int x - расположение модели по оси x
int y - расположение модели по оси у
int width - ширина модели
int height - высота модели

public default void drawModel(Model m,int x, int y, double angle) - отрисовка модели сохраняя оригинальные размеры c поворотом модели относительно центра
Model m - отрисовываемая модель
int x - расположение модели по оси x
int y - расположение модели по оси у
double radian - угол поворота в радианах

public default void drawModel(Model m,int x, int y, int width, int height) - отрисовка модели с регулироемыми размерами c поворотом модели относительно центра
Model m - отрисовываемая модель
int x - расположение модели по оси x
int y - расположение модели по оси у
int width - ширина модели
double radian - угол поворота в радианах
int height - высота модели
