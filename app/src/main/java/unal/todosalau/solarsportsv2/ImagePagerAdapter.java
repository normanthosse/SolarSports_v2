package unal.todosalau.solarsportsv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    //array de img
    private int[] imageIds;
    //array de txt
    private String [] titlesTips;
    //inflar vistas desde XML
    private LayoutInflater inflater;


    //constructor
    public ImagePagerAdapter(Context context, int[] imageIds, String[] titlesTips) {
        this.context = context;
        this.imageIds = imageIds;
        this.titlesTips = titlesTips;
        this.inflater = LayoutInflater.from(context);
    }

    //verifica la cantidad de img
    @Override
    public int getCount() {
        return imageIds.length;
    }
    //verifica si la vista es igual del objeto
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //infla la vista ´para ver
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //infla la vista desde XML
        View view = inflater.inflate(R.layout.item_image, container, false);
        //encuentra texto e imageView en la vista
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        // Establece el recurso de imagen y texto en el ImageView
        imageView.setImageResource(imageIds[position]);
        textView.setText(titlesTips[position]);
        // Añade la vista al contenedor del ViewPager
        container.addView(view);
        return view; //; super.instantiateItem(container, position)
    }

    // Elimina la vista de la página cuando ya no es necesaria
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object); // Quita la vista del contenedor
    }

}
