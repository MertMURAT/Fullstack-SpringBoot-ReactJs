import { ImageCard } from "@/components/ImageCard";
import { Template } from "@/components/Template";


export default function GalleryPages() {
    return (
        <Template>
            <h4 className="font-bold text-3xl py-2">Gallery</h4>
            <section className="grid grid-cols-4 gap-8">
                <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />
                <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />
                <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />
                <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />
                         <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />
                         <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />
                         <ImageCard
                    src="https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    name="Kuş"
                    size="10MB"
                    dataUpload="01/01/2024" />
            </section>
        </Template>
    );
}